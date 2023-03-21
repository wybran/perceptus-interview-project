import { useMutation } from "@tanstack/react-query";
import { CommandRequest, SessionRequest } from "./types";
import config from "../../../config.json";

export const useSSH = () => {
    const newSession = useMutation(
        (request: SessionRequest) =>
            fetch(`${config.API_URL}/newSession`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(request)
            }).then((res) => res.json())
    );
    const executeCommand = useMutation((request: CommandRequest) =>
        fetch(`${config.API_URL}/executeCommand`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(request)
        }).then((res) => res.json())
    );
    return { newSession, executeCommand };
};
