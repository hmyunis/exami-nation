import React from 'react';
import { Link } from 'react-router-dom';
import Footer from './components/Common/Footer';

const App = () => {
    return (
        <>
            <main className="flex flex-col gap-8 justify-center items-center bg-gray-200 h-[92vh]">
                <h1 className="w-fit text-6xl font-light">Welcome to ExamiNation!</h1>
                <Link
                    to="/login"
                    className="px-8 py-3 rounded-full bg-blue-600 hover:bg-blue-700 text-white text-sm hover:scale-105 duration-500"
                >
                    Login to your account
                </Link>
            </main>
            <Footer />
        </>
    );
};

export default App;
