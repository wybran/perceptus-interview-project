import { ActiveSessions } from "../components/ActiveSessions";
import { ConnectForm } from "../components/ConnectForm";

export default function Home() {
    return (
        <main className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 mt-5">
                    <h1>Connect to SSH server</h1>
                    <ConnectForm />
                    <ActiveSessions />
                </div>
            </div>
        </main>
    );
}
