import { HistoryTable } from "../components/HistoryTable";
import { useHistory } from "../features/history/useHistory";

export default function History() {
    const { historyData } = useHistory();

    return (
        <main className="container">
            <div className="row">
                <div className="col-md-8 offset-md-2 mt-5">
                    <h1>Commands History</h1>
                    {historyData && <HistoryTable data={historyData} />}
                </div>
            </div>
        </main>
    );
}
