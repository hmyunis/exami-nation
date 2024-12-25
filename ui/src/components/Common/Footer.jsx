import React from 'react';

const Footer = () => {
    return (
        <footer className="bg-slate-900 p-4">
            <p className="text-center text-white">
                Copyright &copy; {new Date().getFullYear()} ExamiNation
            </p>
        </footer>
    );
};

export default Footer;
