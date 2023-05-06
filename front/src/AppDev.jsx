import './App.css';
import { useRoutes, Outlet, BrowserRouter, Routes, Route } from 'react-router-dom';
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
import SignUpPage from './components/pages/SignUpPage';
import PrivateRoutes from './utils/PrivateRoutes';
import UsersPage from './components/pages/dev/UsersPage';
export default function AppDev() {
  const[searchValue, setSearchValue] = useState();
  const routes = [
    { path: '/', label: "Главная" },
    { path: '/Books', label: "Книги" },
    { path: '/Authors', label: "Авторы" },
    { path: '/Genres', label: "Жанры" },
    { path: '/Forum', label: 'Форум'},
    { path: '/Users', label: 'Пользователи'}
  ];
  return (
    <>
    <BrowserRouter>
        <Header links={routes} setSearchValue={setSearchValue} />
        <div>
            <Routes>
              <Route element={<PrivateRoutes role={"USER"}/>}>
                <Route element={<MainPage/>} path="/" exact />
                <Route element={<MainPage />} path="*" />
                <Route element={<BooksPage searchValue={searchValue} setSearchValue={setSearchValue}/>} path="/Books"/>
                <Route element={<AuthorsPage/>} path="/Authors"/>
                <Route element={<GenresPage/>} path="/Genres"/>
                <Route element={<ForumPage/>} path="/Forum"/>
                <Route element={<Contacts/>} path="/Contacts"/>
              </Route>
              <Route element={<PrivateRoutes role={"ADMIN"}/>}>
                <Route element={<UsersPage/>} path="/Users"/>
              </Route>
              <Route element={<LoginPage/>} path="/Login"/>
              <Route element={<SignUpPage/>} path="/Signup"/>
            </Routes>
        </div>
        <Footer link={{path: '/Contacts'}}/>
      </BrowserRouter>
    </>
  );
}