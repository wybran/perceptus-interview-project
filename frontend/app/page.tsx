"use client";
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
    IP: string;
    username: string;
    password: string;
    port: number;
    command: string;
};

export default function Home() {
    const {
        register,
        handleSubmit
    } = useForm<Inputs>();
    const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data);

    return (
        <main>
            <h1>Connect to SSH server</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="form-group">
                    <label htmlFor="IP">IP</label>
                    <input
                        {...register("IP", { required: true })}
                        type="text"
                        className="form-control"
                        id="IP"
                        placeholder="Enter IP address"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input
                        {...register("username", { required: true })}
                        type="text"
                        className="form-control"
                        id="username"
                        placeholder="Enter username"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        {...register("password", { required: true })}
                        type="password"
                        className="form-control"
                        id="password"
                        placeholder="Enter password"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="port">Port</label>
                    <input
                        {...register("port", { required: true })}
                        type="number"
                        className="form-control"
                        id="port"
                        placeholder="Enter port"
                        defaultValue={22}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="command">Command</label>
                    <input
                        {...register("command", { required: true })}
                        type="text"
                        className="form-control"
                        id="command"
                        placeholder="Enter command"
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Submit
                </button>
            </form>
        </main>
    );
}
