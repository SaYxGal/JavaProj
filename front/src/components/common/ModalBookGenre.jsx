import React, { useEffect } from "react";
export default function ModalBookGenre(props) {
    useEffect(() => {
      console.log(props.state);
    }, [props.state]);
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
                  Обновление жанров книги
                </h1>
                <button
                  className="btn-close"
                  type="button"
                  aria-label="Close"
                  onClick={() => props.setVisible(false)}
                ></button>
              </div>
              <div className="modal-body">
                <div>
                  <p className="pt-2 pb-0">Выберите жанр</p>
                  <select className="form-control" id="genre" value={props.state} onChange={(event) => props.setState(event.target.value)} required>
                  {
                    props.genres.map((item, index) => (
                      <option key={item.id} value={item.id}>
                          {item.name}
                      </option>
                    ))
                  }
                  </select>
                  <div>
                    <button className="btn btn-secondary" type="button" onClick={() => props.setVisible(false)}>Отмена</button>
                    <button className="btn btn-primary m-2" type="button" onClick={() => props.onAdd(props.state)}>Добавить</button>
                    <button className="btn btn-primary m-2" type="button" onClick={() => props.onRemove(props.state)}>Удалить</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      );
}