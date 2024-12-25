import React from 'react';
import { Link } from 'react-router-dom';

const LoginPage = () => {
    return (
        <section className="h-[100vh] bg-slate-800">
            <div className="text-center p-8 md:p-24">
                <div className="bg-white p-8 rounded-lg shadow-lg max-w-md mx-auto">
                    <Link
                        to="/"
                        className="size-24 mx-auto mb-4 block rounded-full bg-blue-400 shadow-md"
                    ></Link>
                    <h1 className="text-2xl font-bold">Welcome to ExamiNation!</h1>
                    <h2 className="my-4 font-light text-gray-700">
                        Please sign in to your account.
                    </h2>
                    <form className="space-y-4">
                        <div>
                            <input
                                className="w-full border border-gray-300 px-4 py-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                type="text"
                                name="username"
                                id="username"
                                placeholder="Username"
                            />
                        </div>
                        <div>
                            <input
                                className="w-full border border-gray-300 px-4 py-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Password"
                            />
                        </div>
                        <button
                            className="bg-blue-400 block w-full hover:bg-blue-500 text-white font-bold py-2 px-4 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            type="submit"
                        >
                            Sign In
                        </button>
                    </form>
                </div>
            </div>
        </section>
    );
};

export default LoginPage;
