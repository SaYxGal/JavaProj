import Toolbar from "../../common/Toolbar";
import Table from "../../common/Table"
import DataService from "../../../services/DataService";
import { useState, useEffect } from "react";
import React from "react";
import AuthorDto from "../../../modelsDTO/AuthorDto";
import ModalForAuthorAndGenre from "../../common/ModalForAuthorAndGenre";
export default function AuthorsPage(props) {
    const dataUrlPrefix = import.meta.env.VITE_API_URL;
    const authorUrl = "/authors";
    const [items, setItems] = useState([]);
    const [data, setData] = useState(new AuthorDto());
    const [modalHeader, setModalHeader] = useState('');
    const [modalConfirm, setModalConfirm] = useState('');
    const [modalVisible, setModalVisible] = useState(false);
    const [isEdit, setEdit] = useState(false);
    const authorTransformer = (data) => new AuthorDto(data);
    const catalogAuthorHeaders = [
        { name: 'name', label: 'Имя' },
        { name: 'surname', label: 'Фамилия' },
        { name: 'patronymic', label: 'Отчество' },
    ];
    
    let selectedItems = [];

    useEffect(() => {
        loadItems();
    }, []);
    function handleFormChange(event) {
        setData({ ...data, [event.target.id]: event.target.value })
    }

    function loadItems() {
        DataService.readAll(dataUrlPrefix, authorUrl, authorTransformer)
            .then(data => setItems(data));
    }

    function saveItem() {
        if (!isEdit) {
            DataService.createAuthor(dataUrlPrefix, authorUrl , data).then(() => loadItems());
        } else {
            DataService.updateAuthor(dataUrlPrefix, authorUrl , data).then(() => loadItems());
        }
    }

    function handleAdd() {
        setEdit(false);
        setModalHeader('Добавление автора');
        setModalConfirm('Добавить');
        setModalVisible(true);
        setData(new AuthorDto())
    }

    function handleEdit() {
        if (selectedItems.length === 0) {
            return;
        }
        edit(selectedItems[0]);
    }

    function edit(editedId) {
        DataService.read(dataUrlPrefix, authorUrl, editedId, authorTransformer)
            .then(data => {
                setEdit(true);
                setModalHeader('Редактирование автора');
                setModalConfirm('Сохранить');
                setModalVisible(true);
                setData(new AuthorDto(data))
            });
    }

    function handleRemove() {
        if (selectedItems.length === 0) {
            return;
        }
        if (confirm('Удалить выбранные элементы?')) {
            const promises = [];
            selectedItems.forEach(item => {
                promises.push(DataService.delete(dataUrlPrefix, authorUrl, item));
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
                headers={catalogAuthorHeaders} 
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
                <label htmlFor="name" className="form-label">Имя</label>
                <input type="text" id="name" className="form-control" required
                    value={data.name} onChange={handleFormChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="surname" className="form-label">Фамилия</label>
                    <input type="text" id="surname" className="form-control" required
                        value={data.surname} onChange={handleFormChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="patronymic" className="form-label">Отчество</label>
                    <input type="text" id="patronymic" className="form-control" required
                        value={data.patronymic} onChange={handleFormChange}/>
                </div>
            </ModalForAuthorAndGenre>
        </>
    );
}