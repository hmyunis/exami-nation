import React from 'react';

const NotFound = () => {
    return (
        <div className="flex flex-col justify-center items-center h-[100vh] bg-slate-700 text-white">
            <h1 className="text-red-500 text-6xl mb-4">404 - Page Not Found</h1>
            <p>The page you're looking for does not exist.</p>
        </div>
    );
};

export default NotFound;
