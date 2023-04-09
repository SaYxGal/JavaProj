import React, { useEffect } from "react";
import { useState} from "react";
import AuthorDto from "../../modelsDTO/AuthorDto"
export default function Modal(props) {
  const dataUrlPrefix = import.meta.env.VITE_API_URL;
  const authorUrl = "/authors";
  const authorTransformer = (data) => new AuthorDto(data);
  const formRef = React.createRef();
  function done(e) {
    e.preventDefault();
    if (formRef.current.checkValidity()) {
        props.onDone()
        props.onHide(false)
    } else {
      formRef.current.reportValidity();
    }
  }
  return (
    <div
      className="modal fade show"
      tabIndex="-1"
      aria-hidden="true"
      style={{ display: props.visible ? "block" : "none" }}
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="exampleModalLabel">
              {props.header}
            </h1>
            <button
              className="btn-close"
              type="button"
              aria-label="Close"
              onClick={() => props.onHide(true)}
            ></button>
          </div>
          <div className="modal-body">
            <form ref={formRef} onSubmit={done}>
              <p className="pt-2 pb-0">Введите название книги</p>
              <input className="form-control" type="text" id="name" value={props.state.name} onChange={(event) => props.setState(event)} placeholder="Введите название" required/>
              <p className="pt-2 pb-0">Описание книги</p>
              <textarea className="form-control" id="description" value={props.state.description} onChange={(event) => props.setState(event)} required></textarea>
              <p className="pt-2 pb-0">Выберите автора</p>
              <select className="form-control" id="author" value={props.state.author} onChange={(event) => props.setState(event)} required>
              {
                props.authors.map((item, index) => (
                  <option key={item.id} value={item.id}>
                      {item.name + " " + item.surname}
                  </option>
                ))
              }
              </select>
              {
                props.isEdit === false &&
                <>
                <p className="pt-2 pb-0">Выберите жанры</p>
                <ul className="list-group">
                  {
                    props.genres.map((item, index) => (
                      <li key={index} className="list-group-item">
                        <input className="form-check-input me-1" id={index} type="checkbox" aria-label="..." value={item.id} checked={props.checkboxes[index]}
                          onChange={() => props.setCheckboxes(index, item.id)}/>
                        {item.name}
                      </li>
                    ))
                  }
                </ul>
                </>
              }
              <div>
                <button className="btn btn-secondary" type="button" onClick={() => props.onHide(true)}>Отмена</button>
                <button className="btn btn-primary m-2" type="submit">{props.confirm}</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
