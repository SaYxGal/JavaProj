import Toolbar from "../../common/Toolbar";
import Table from "../../common/Table"
import DataService from "../../../services/DataService";
import { useState, useEffect } from "react";
import React from "react";
import GenreDto from "../../../modelsDTO/GenreDto";
import ModalForAuthorAndGenre from "../../common/ModalForAuthorAndGenre";
export default function GenresPage(props) {
  const dataUrlPrefix = import.meta.env.VITE_API_URL;
    const genreUrl = "/genres";
    const [items, setItems] = useState([]);
    const [data, setData] = useState(new GenreDto());
    const [modalHeader, setModalHeader] = useState('');
    const [modalConfirm, setModalConfirm] = useState('');
    const [modalVisible, setModalVisible] = useState(false);
    const [isEdit, setEdit] = useState(false);
    const genreTransformer = (data) => new GenreDto(data);
    const catalogGenreHeaders = [
        { name: 'name', label: 'Название жанра' },
    ];
    
    let selectedItems = [];

    useEffect(() => {
        loadItems();
    }, []);
    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value })
    }

    function loadItems() {
        DataService.readAll(dataUrlPrefix, genreUrl, genreTransformer)
            .then(data => setItems(data));
    }

    function saveItem() {
        if (!isEdit) {
            DataService.createGenre(dataUrlPrefix, genreUrl , data).then(() => loadItems());
        } else {
            DataService.updateGenre(dataUrlPrefix, genreUrl , data).then(() => loadItems());
        }
    }

    function handleAdd() {
        setEdit(false);
        setModalHeader('Добавление жанра');
        setModalConfirm('Добавить');
        setModalVisible(true);
        setData(new GenreDto())
    }

    function handleEdit() {
        if (selectedItems.length === 0) {
            return;
        }
        edit(selectedItems[0]);
    }

    function edit(editedId) {
        DataService.read(dataUrlPrefix, genreUrl, editedId, genreTransformer)
            .then(data => {
                setEdit(true);
                setModalHeader('Редактирование жанра');
                setModalConfirm('Сохранить');
                setModalVisible(true);
                setData(new GenreDto(data))
            });
    }

    function handleRemove() {
        if (selectedItems.length === 0) {
            return;
        }
        if (confirm('Удалить выбранные элементы?')) {
            const promises = [];
            selectedItems.forEach(item => {
                promises.push(DataService.delete(dataUrlPrefix, genreUrl, item));
            });
            Promise.all(promises).then((results) => {
                selectedItems.length = 0;
                loadItems();
            });
        }
    }

    function handleTableClick(tableSelectedItems) {
        selectedItems = tableSelectedItems;
    }

    function handleTableDblClick(tableSelectedItem) {
        edit(tableSelectedItem);
    }

    function handleModalHide() {
        setModalVisible(false);
    }

    function handleModalDone() {
        saveItem();
    }

    return (
        <>
            <Toolbar 
                onAdd={handleAdd}
                onEdit={handleEdit}
                onRemove={handleRemove}/>
            <Table 
                headers={catalogGenreHeaders} 
                items={items}
                selectable={true}
                onClick={handleTableClick}
                onDblClick={handleTableDblClick}/>
            <ModalForAuthorAndGenre 
                header={modalHeader}
                confirm={modalConfirm}
                visible={modalVisible} 
                onHide={handleModalHide}
                onDone={handleModalDone}>
                <div className="mb-3">
                <label htmlFor="name" className="form-label">Название жанра</label>
                <input type="text" id="name" className="form-control" required
                    value={data.name} onChange={handleFormChange}/>
                </div>
            </ModalForAuthorAndGenre>
        </>
    );
}
