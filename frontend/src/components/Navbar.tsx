import clsx from "clsx";
import Link from "next/link";
import { useRouter } from "next/router";

const LINKS = [
    {
        title: "Connect to SSH server",
        href: "/"
    },
    {
        title: "Commands history",
        href: "/history"
    }
];

export const Navbar = () => {
    const { asPath } = useRouter();

    const isActive = (href: string) => href === asPath;

    return (
        <ul className="nav justify-content-center">
            {LINKS.map((link) => (
                <li key={link.href} className="nav-item">
                    <Link
                        href={link.href}
                        className={
                            (clsx(isActive(link.href) ? "active" : ""),
                            "nav-link")
                        }>
                        {link.title}
                    </Link>
                </li>
            ))}
        </ul>
    );
};
