import { useSSH } from "@/src/features/ssh/useSSH";
import { useRouter } from "next/router";
import { useState } from "react";
import Terminal, { ColorMode, TerminalOutput } from "react-terminal-ui";

export default function Console() {
    const router = useRouter();
    const params = router.query;
    const uuid = params.uuid as string;

    const { executeCommand } = useSSH();

    const [terminalLineData, setTerminalLineData] = useState([
        <TerminalOutput>Welcome to the React Terminal UI Demo!</TerminalOutput>
    ]);

    const onInput = (input: string) => {
        setTerminalLineData((prevTerminalLineData) => [
            ...prevTerminalLineData,
            <TerminalOutput>{input}</TerminalOutput>
        ]);
        executeCommand.mutate({ uuid: uuid, command: input }, {
            onSuccess: (response) => {
                response.text().then((data) => {
                    if(response.status !== 200) {
                        console.error(data);
                        return;
                    }
                    setTerminalLineData((prevTerminalLineData) => [
                        ...prevTerminalLineData,
                        <TerminalOutput>{data}</TerminalOutput>
                    ]);
                }
            )
        }});
    };

    return (
        <div className="container">
            <Terminal
                name="React Terminal Usage Example"
                colorMode={ColorMode.Light}
                onInput={onInput}>
                {terminalLineData}
            </Terminal>
        </div>
    );
}
