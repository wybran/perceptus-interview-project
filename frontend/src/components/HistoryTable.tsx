import React, { useState } from "react";
import {
    createColumnHelper,
    flexRender,
    getCoreRowModel,
    getFilteredRowModel,
    getPaginationRowModel,
    useReactTable
} from "@tanstack/react-table";
import { CommandHistoryResponse } from "../features/history/types";

interface HistoryTableProps {
    data: CommandHistoryResponse[];
}

export const HistoryTable = ({ data }: HistoryTableProps) => {
    const [globalFilter, setGlobalFilter] = useState("");

    const columnHelper = createColumnHelper<CommandHistoryResponse>();

    const columns = React.useMemo(
        () => [
            columnHelper.accessor("command", {
                header: "Command"
            }),
            columnHelper.accessor("hostIP", {
                header: "Host IP"
            }),
            columnHelper.accessor("created_at", {
                header: "Created At",
                cell: (row) => new Date(row.getValue()).toLocaleString()
            })
        ],
        []
    );

    const table = useReactTable<CommandHistoryResponse>({
        data: data,
        columns,
        state: {
            globalFilter
        },
        onGlobalFilterChange: setGlobalFilter,
        getFilteredRowModel: getFilteredRowModel(),
        getPaginationRowModel: getPaginationRowModel(),
        getCoreRowModel: getCoreRowModel()
    });

    return (
        <>
            <input
                type="text"
                className="form-control mb-2"
                placeholder="Search..."
                value={globalFilter ?? ''}
                onChange={(e) => table.setGlobalFilter(e.target.value)}
            />
            <table className="table table-striped table-dark">
                <thead>
                    {table.getHeaderGroups().map((headerGroup) => (
                        <tr key={headerGroup.id}>
                            {headerGroup.headers.map((header) => (
                                <th key={header.id} scope="col">
                                    {header.isPlaceholder
                                        ? null
                                        : flexRender(
                                              header.column.columnDef.header,
                                              header.getContext()
                                          )}
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody>
                    {table.getRowModel().rows.map((row) => (
                        <tr key={row.id}>
                            {row.getVisibleCells().map((cell) => (
                                <td key={cell.id}>
                                    {flexRender(
                                        cell.column.columnDef.cell,
                                        cell.getContext()
                                    )}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="flex items-center gap-2">
                <button
                    className="border rounded p-1"
                    onClick={() => table.setPageIndex(0)}
                    disabled={!table.getCanPreviousPage()}>
                    {"<<"}
                </button>
                <button
                    className="border rounded p-1"
                    onClick={() => table.previousPage()}
                    disabled={!table.getCanPreviousPage()}>
                    {"<"}
                </button>
                <button
                    className="border rounded p-1"
                    onClick={() => table.nextPage()}
                    disabled={!table.getCanNextPage()}>
                    {">"}
                </button>
                <button
                    className="border rounded p-1"
                    onClick={() => table.setPageIndex(table.getPageCount() - 1)}
                    disabled={!table.getCanNextPage()}>
                    {">>"}
                </button>
                <span className="flex items-center gap-1">
                    <div>Page</div>
                    <strong>
                        {table.getState().pagination.pageIndex + 1} of{" "}
                        {table.getPageCount()}
                    </strong>
                </span>
                <span className="flex items-center gap-1">
                    | Go to page:
                    <input
                        type="number"
                        defaultValue={table.getState().pagination.pageIndex + 1}
                        onChange={(e) => {
                            const page = e.target.value
                                ? Number(e.target.value) - 1
                                : 0;
                            table.setPageIndex(page);
                        }}
                        className="border p-1 rounded w-16"
                    />
                </span>
                <select
                    value={table.getState().pagination.pageSize}
                    onChange={(e) => {
                        table.setPageSize(Number(e.target.value));
                    }}>
                    {[10, 20, 30, 40, 50].map((pageSize) => (
                        <option key={pageSize} value={pageSize}>
                            Show {pageSize}
                        </option>
                    ))}
                </select>
            </div>
        </>
    );
};