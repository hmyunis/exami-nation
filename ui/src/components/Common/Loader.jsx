import React from 'react';

const Loader = () => {
    return (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50 w-full h-screen z-100">
            <div className="flex space-x-2">
                <div className="w-4 h-4 bg-gray-500 rounded-full animate-bounce-delay"></div>
                <div className="w-4 h-4 bg-gray-500 rounded-full animate-bounce-delay2"></div>
                <div className="w-4 h-4 bg-gray-500 rounded-full animate-bounce-delay3"></div>
            </div>
        </div>
    );
};

export default Loader;
