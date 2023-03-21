import { useRouter } from "next/router";
import { useSSH } from "../features/ssh/useSSH";

export const ActiveSessions = () => {
    const router = useRouter();
    const { activeSessions, deleteSession } = useSSH();

    return (
        <>
            <h2 className="mt-5">Active sessions</h2>
            {activeSessions &&
                activeSessions.map((session) => {
                    return (
                        <div className="card mt-3">
                            <div className="card-body">
                                <h5 className="card-title">
                                    {session.username}@{session.hostIP}:{session.port}
                                </h5>
                                <button className="btn btn-primary m-1" onClick={() => {
                                    router.push(`/console/${session.uuid}`);
                                }}>
                                    Connect
                                </button>
                                <button
                                    className="btn btn-danger"
                                    onClick={() => {
                                        deleteSession.mutate(session.uuid);
                                    }}>
                                    Close session
                                </button>
                            </div>
                        </div>
                    );
                })}
                {activeSessions.length === 0 && <p>No active sessions</p>}
        </>
    );
};
