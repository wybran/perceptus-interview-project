export interface SessionRequest {
    ip: string;
    port: number;
    username: string;
    password: string;
}
export interface CommandRequest {
    uuid: string;
    command: string;
}
export interface SessionResponse {
    uuid: string;
    hostIP: string;
    port: number;
    username: string;
}

