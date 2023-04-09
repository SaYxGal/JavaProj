import './App.css';
import { useRoutes, Outlet, BrowserRouter } from 'react-router-dom';
import Header from './components/common/Header';
import MainPage from './components/pages/MainPage';
import Footer from './components/common/Footer';
import BooksCatalog from './components/pages/dev/BooksPage';
import ForumPage from './components/pages/ForumPage';
import Contacts from './components/pages/Contacts';
import LoginPage from './components/pages/LoginPage';
import { useState } from 'react';
function Router(props) {
    return useRoutes(props.rootRoute);
  }
  
  export default function AppProd() {
    const[searchValue, setSearchValue] = useState();
    const routes = [
      { index: true, element: <MainPage />},
      { path: '', element: <MainPage/>, label: 'Главная' },
      { path: 'books', element: <BooksCatalog searchValue={searchValue}/>, label: 'Книги' },
      { path: 'authors', element: null, label: 'Авторы' },
      { path: 'forum', element: <ForumPage/>, label: 'Форум' },
      { path: 'login', element: <LoginPage/>},
      { path: 'contacts', element: <Contacts/>}
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