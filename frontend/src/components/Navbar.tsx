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
        <nav className="navbar navbar-dark bg-dark navbar-expand-lg justify-content-center">
            <ul className="navbar-nav">
                {LINKS.map(({ title, href }) => (
                    <li key={href} className="nav-item">
                        <Link
                            href={href}
                            className={clsx(
                                "nav-link",
                                isActive(href) && "active"
                            )}>
                            {title}
                        </Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
};
