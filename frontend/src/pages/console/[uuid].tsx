import { useSSH } from "@/src/features/ssh/useSSH";
import { useRouter } from "next/router";
import { useState } from "react";
import Terminal, { ColorMode, TerminalOutput } from "react-terminal-ui";
import { toast } from "react-toastify";

export default function Console() {
    const router = useRouter();
    const params = router.query;
    const uuid = params.uuid as string;

    const { executeCommand, deleteSession } = useSSH();

    const [terminalLineData, setTerminalLineData] = useState([
        <TerminalOutput>SSH session successfully connected</TerminalOutput>
    ]);

    const onInput = (input: string) => {
        setTerminalLineData((prevTerminalLineData) => [
            ...prevTerminalLineData,
            <TerminalOutput>{input}</TerminalOutput>
        ]);
        executeCommand.mutate(
            { uuid: uuid, command: input },
            {
                onSuccess: (response) => {
                    if (!response.isError)
                        setTerminalLineData((prevTerminalLineData) => [
                            ...prevTerminalLineData,
                            <TerminalOutput>{response.output}</TerminalOutput>
                        ]);
                    else
                        setTerminalLineData((prevTerminalLineData) => [
                            ...prevTerminalLineData,
                            <TerminalOutput>
                                <b>{response.output}</b>
                            </TerminalOutput>
                        ]);
                },
                onError: () => {
                    toast.error("Błąd sesji SSH 😢");
                    router.push("/");
                }
            }
        );
    };

    return (
        <div className="container">
            <button
                className="btn btn-danger m-3"
                onClick={() => {
                    deleteSession.mutate(uuid);
                    router.push("/");
                }}>
                Close session
            </button>
            <div className="row">
                <Terminal
                    name="SSH Console"
                    colorMode={ColorMode.Dark}
                    onInput={onInput}>
                    {terminalLineData}
                </Terminal>
            </div>
        </div>
    );
}
