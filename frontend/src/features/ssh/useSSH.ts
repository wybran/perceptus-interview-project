import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { CommandRequest, SessionRequest, SessionResponse } from "./types";
import config from "../../../config.json";

export const useSSH = () => {
    const queryClient = useQueryClient();

    const newSession = useMutation((request: SessionRequest) =>
        fetch(`${config.API_URL}/session`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(request)
        }).then((res) => res.json())
    );
    const executeCommand = useMutation((request: CommandRequest) =>
        fetch(`${config.API_URL}/session/executeCommand`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(request)
        }).then((res) => res.json())
    );
    const { data: activeSessions } = useQuery<SessionResponse[]>(
        ["sessions"],
        async () => await (await fetch(`${config.API_URL}/session`)).json()
    );
    const deleteSession = useMutation(
        (uuid: string) =>
            fetch(`${config.API_URL}/session/${uuid}`, {
                method: "DELETE"
            }),
        {
            onSuccess: () => {
                queryClient.invalidateQueries({ queryKey: ["sessions"] });
            }
        }
    );

    return {
        newSession,
        executeCommand,
        activeSessions: activeSessions ?? [],
        deleteSession
    };
};
