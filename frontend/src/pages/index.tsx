import { SessionRequest } from "@/src/features/ssh/types";
import { useSSH } from "@/src/features/ssh/useSSH";
import { useRouter } from "next/router";
import { useForm, SubmitHandler } from "react-hook-form";
import { toast } from "react-toastify";

export default function Home() {
    const router = useRouter();
    const { register, handleSubmit } = useForm<SessionRequest>();

    const { newSession } = useSSH();

    const onSubmit: SubmitHandler<SessionRequest> = async (
        request: SessionRequest
    ) => {
        await toast
            .promise(newSession.mutateAsync(request), {
                pending: "Tworzenie nowej sesji...",
                success: "Sesja utworzona 👌",
                error: `Błąd podczas tworzenia sesji 😢`
            })
            .then((data) => {
                router.push(`/console/${data.uuid}`);
            });
    };

    return (
        <main>
            <h1>Connect to SSH server</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="form-group">
                    <label htmlFor="IP">IP</label>
                    <input
                        {...register("ip", { required: true })}
                        type="text"
                        className="form-control"
                        id="ip"
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
                <button type="submit" className="btn btn-primary">
                    Submit
                </button>
            </form>
        </main>
    );
}
