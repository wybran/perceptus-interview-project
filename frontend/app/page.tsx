
export default function Home() {
    return (
        <main>
            <h1>Connect to SSH server</h1>
            <form>
                <div className="form-group">
                    <label htmlFor="IP">IP</label>
                    <input type="text" className="form-control" id="IP" placeholder="Enter IP address" />
                </div> 
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input type="text" className="form-control" id="username" placeholder="Enter username" />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input type="password" className="form-control" id="password" placeholder="Enter password" />
                </div>
                <div className="form-group">
                    <label htmlFor="port">Port</label>
                    <input type="number" className="form-control" id="port" placeholder="Enter port" defaultValue={22}/>
                </div>
                <div className="form-group">
                    <label htmlFor="command">Command</label>
                    <input type="text" className="form-control" id="command" placeholder="Enter command" />
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </main>
    );
}
