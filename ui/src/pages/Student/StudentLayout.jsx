import React from 'react';
import StudentSidebar from './../../components/Student/StudentSidebar.jsx';
import StudentDashboard from './StudentDashboard.jsx';

const StudentLayout = () => {
    return (
        <>
            <StudentSidebar />
            <StudentDashboard />
        </>
    );
};

export default StudentLayout;
