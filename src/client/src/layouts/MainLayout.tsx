import { Outlet } from 'react-router-dom';
import Sidebar from '../components/layout/Sidebar';

const MainLayout = () => {
  return (
    <div className="flex min-h-screen">
      <Sidebar />
      <div className="flex flex-col flex-1">
        <main className="flex-1 p-6 flex justify-center items-center">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default MainLayout;
