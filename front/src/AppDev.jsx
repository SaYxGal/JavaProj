import './App.css';
import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import Header from './components/common/Header';
import MainPage from './components/pages/MainPage';
import Footer from './components/common/Footer';
import BooksPage from './components/pages/dev/BooksPage';
import Contacts from './components/pages/Contacts';
import LoginPage from './components/pages/LoginPage';
import { useState } from 'react';
import AuthorsPage from './components/pages/dev/AuthorsPage';
import GenresPage from './components/pages/dev/GenresPage';
import ForumPage from './components/pages/ForumPage'
function Router(props) {
  return useRoutes(props.rootRoute);
}

export default function AppDev() {
  const[searchValue, setSearchValue] = useState();
  const routes = [
    { index: true, element: <MainPage />},
    { path: '', element: <MainPage/>, label: 'Главная' },
    { path: 'Books', element: <BooksPage searchValue={searchValue} setSearchValue={setSearchValue}/>, label: 'Книги' },
    { path: 'Authors', element: <AuthorsPage/>, label: 'Авторы' },
    { path: 'Genres', element: <GenresPage/>, label: 'Жанры' },
    { path: 'Forum', element:<ForumPage/>, label: 'Форум'},
    { path: 'Login', element: <LoginPage/>},
    { path: 'Contacts', element: <Contacts/>}
  ];
  const links = routes.filter(route => route.hasOwnProperty('label'));
  const rootRoute = [
    { path: '/', element: render(links), children: routes }
  ];

  function render(links) {
    return (
      <>
        <Header links={links} single={routes[routes.length-2]} setSearchValue={setSearchValue} />
        <div className="container-fluid">
          <Outlet />
        </div>
        <Footer link={routes[routes.length-1]}/>
      </>
    );
  }

  return (
    <BrowserRouter>
      <Router rootRoute={ rootRoute } />
    </BrowserRouter>
  );
}