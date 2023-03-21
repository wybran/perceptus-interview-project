import { useQuery } from "@tanstack/react-query";
import config from "../../../config.json";
import { CommandHistoryResponse } from "./types";

export const useHistory = () => {
    const {
        data: historyData,
        isError: historyIsError,
        isLoading: historyIsLoading
    } = useQuery<CommandHistoryResponse[]>(
        ["history"],
        async () => await (await fetch(`${config.API_URL}/history`)).json()
    );
    return {
        historyData: historyData ?? [],
        historyIsError,
        historyIsLoading
    };
}