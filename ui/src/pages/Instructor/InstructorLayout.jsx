import React from 'react';
import InstructorSidebar from './../../components/Instructor/InstructorSidebar.jsx';
import InstructorDashboard from './InstructorDashboard.jsx';

const InstructorLayout = () => {
    return (
        <>
            <InstructorSidebar />
            <InstructorDashboard />
        </>
    );
};

export default InstructorLayout;
