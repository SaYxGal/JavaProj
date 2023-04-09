import { useState, useEffect } from "react";
import React from "react";
import Modal from "../../common/Modal";
import BookDto from "../../../modelsDTO/BookDto";
import Container from "../../common/Container";
import GenresList from "../../common/GenresList";
import DataService from "../../../services/DataService";
import AuthorDto from "../../../modelsDTO/AuthorDto";
import GenreDto from "../../../modelsDTO/GenreDto";
import ModalBookGenre from "../../common/ModalBookGenre";
export default function BooksPage() {
  const dataUrlPrefix = import.meta.env.VITE_API_URL;
  const booksUrl = "/books";
  const authorUrl = "/authors";
  const genresUrl = "/genres";
  const [data, setData] = useState(new BookDto());
  const [books, setBooks] = useState([]);
  const [genres, setGenres] = useState([]);
  const [authors, setAuthors] = useState([]);
  const [modalHeader, setModalHeader] = useState("");
  const [modalConfirm, setModalConfirm] = useState("");
  const [modalVisible, setModalVisible] = useState(false);
  const [isEdit, setEdit] = useState(false);

  const [genresId, setGenresId] = useState([])
  const [stateModal, setStateModal] = useState({
    name : "",
    description: "",
    author: 0,
  });
  const handleInputChange = (event) => {
    setStateModal({ ...stateModal, [event.target.id]: event.target.value });
  };
  const [checkedStateModal, setCheckedStateModal] = useState();
  const handleOnChange = (position, id) => {
    const updatedCheckedState = checkedStateModal.map((item, index) =>
      index === position ? !item : item
    );
    setGenresId(oldArray => [...oldArray, id]);
    setCheckedStateModal(updatedCheckedState);
  };

  const [stateModalGenre, setStateModalGenre] = useState(0);
  const [modalGenreVisible, setModalGenreVisible] = useState(false);

  const bookTransformer = (data) => new BookDto(data);
  const authorTransformer = (data) => new AuthorDto(data);
  const genreTransformer = (data) => new GenreDto(data);
  useEffect(() => {
    loadBooks();
    loadGenres();
  }, []);
  useEffect(() =>{
    setCheckedStateModal(new Array(genres.length).fill(false))
  }, [genres])
  function loadBooks() {
    DataService.readAll(dataUrlPrefix, booksUrl, bookTransformer).then((data) =>
      setBooks(data)
    );
  }
  function loadGenres(){
    DataService.readAll(dataUrlPrefix, genresUrl, genreTransformer).then((data) =>
      setGenres(data)
    );
  }
  function saveItem() {
    if (!isEdit) {
        console.log(data)
      DataService.createBook(dataUrlPrefix , booksUrl, data).then(() => loadBooks());
    } else {
      console.log(data)
      DataService.updateBook(dataUrlPrefix, booksUrl, data).then(() =>
        loadBooks()
      );
    }
  }

  function handleAdd() {
    DataService.readAll(dataUrlPrefix, genresUrl, genreTransformer).then((elements)=>{
      DataService.readAll(dataUrlPrefix, authorUrl, authorTransformer).then((data)=>{
        setAuthors(data);
        setGenres(elements);
        setEdit(false);
        setModalHeader("Добавление элемента");
        setModalConfirm("Добавить");
        setModalVisible(true);
        setData(new BookDto());
      })
    })
  }
  function handleEdit(id) {
    edit(id);
  }
  function edit(editedId) {
    DataService.readAll(dataUrlPrefix, authorUrl, authorTransformer).then((items)=>{
      DataService.read(dataUrlPrefix, booksUrl, editedId, bookTransformer).then((data) => {
        setAuthors(items);
        setEdit(true);
        setModalHeader("Редактирование элемента");
        setModalConfirm("Сохранить");
        setModalVisible(true);
        setStateModal({name: data.name, description: data.description, author: data.author.id})
        setData(new BookDto(data))
      });
    })
  }
  function handleUpdateGenre(id){
    DataService.read(dataUrlPrefix, booksUrl, id, bookTransformer).then((data)=>{
      setModalGenreVisible(true)
      setData(new BookDto(data))
    })
    
  }
  function removeGenre(id){
    console.log(data.genres)
    if(data.genres.filter(elem => elem.id == id).length <= 0){
      alert("Такого жанра у книги нет");
    }
    else{
      DataService.updateBookGenre(dataUrlPrefix, booksUrl, data, id, true).then(() => loadBooks());
    }
  }
  function addGenre(id){
    console.log(data.genres)
    if(data.genres.filter(elem => elem.id == id).length > 0){
      alert("Такой жанр у книги уже есть");
    }
    else{
      DataService.updateBookGenre(dataUrlPrefix, booksUrl, data, id, false).then(() => loadBooks());
    }
  }

  function handleRemove(id) {
    if (confirm("Удалить выбранный элемент?")) {
      DataService.delete(dataUrlPrefix,booksUrl,id).then((results) => {
        loadBooks();
      });
    }
  }

  function handleModalHide(param) {
    setModalVisible(false);
    if(param){
      stateModal.name = "";
      stateModal.description = "";
      stateModal.author = "";
    }
  }

  function handleModalDone() {
    console.log(stateModal);
    console.log(checkedStateModal);
    console.log(genresId)
    if(genresId.length > 0 || isEdit == true){
      data.name = stateModal.name;
      data.description = stateModal.description;
      data.author = stateModal.author;
      data.genres = genresId;
      saveItem();
    }
    stateModal.name="";
    stateModal.description="";
  }

  return (
    <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
        <select className="form-select drop" id="select-genres" aria-label="Default select example">
          
        </select>
        <div className="row justify-content-center">
        <Modal
            header={modalHeader}
            authors={authors}
            genres={genres}
            state={stateModal}
            setState={handleInputChange}
            checkboxes={checkedStateModal}
            setCheckboxes={handleOnChange}
            confirm={modalConfirm}
            visible={modalVisible}
            onHide={handleModalHide}
            onDone={handleModalDone}
            isEdit={isEdit}
        />
        <ModalBookGenre
            state={stateModalGenre}
            genres={genres}
            setState={setStateModalGenre}
            visible={modalGenreVisible}
            setVisible={setModalGenreVisible}
            onRemove={removeGenre}
            onAdd={addGenre}
        />
        <GenresList
            genres={genres}
            onAdd={handleAdd}/>
        <Container
            books={books}
            onRemove={handleRemove}
            onEdit={handleEdit}
            onUpdateGenre={handleUpdateGenre}/>
        </div>
    </div>
  );
}
