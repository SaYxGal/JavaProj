import React, { useEffect } from "react";
import { useState } from "react";
export default function Contacts(props) {
  const formRef = React.createRef();
  const [info, setInfo] = useState({
    name: "",
    theme: "",
    text: "",
    agreement: false
  });
  const handleChange = (event) => {
    setInfo({ ...info, [event.target.name]: event.target.type==="checkbox" ? event.target.checked : event.target.value});
  };
  function handleSubmit(event){
    if (!formRef.current.checkValidity()) {
      event.preventDefault();
      event.stopPropagation();
    }
}
  return (
    <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
      <div className="d-flex justify-content-center">
        <form ref={formRef} className="w-50 ms-2 needs-validation" onSubmit={(e)=>handleSubmit(e)} >
          <h2 className="py-3">Обратная связь</h2>
          <h4>Имя</h4>
          <input
            className="form-control my-2"
            type="text"
            name="name"
            value={info.name}
            onChange={(e)=>handleChange(e)}
            placeholder="Введите имя"
            required
          />
          <div className="py-3">
            <label className="form-label" htmlFor="validationCustom04">
              Тема сообщения
            </label>
            <select
              className="form-select"
              name="theme"
              value={info.theme}
              onChange={(e)=>handleChange(e)}
              id="validationCustom04"
              required
            >
              <option defaultValue={""} disabled>
                Выберите...
              </option>
              <option value="first">Техническая составляющая сайта</option>
              <option value="second">Сообщество и форум</option>
              <option value="third">Ассортимент книг и аудиокниг</option>
              <option value="fourth">Другая тема</option>
            </select>
            <div className="invalid-feedback">
              Выберите что-то из представленного списка.
            </div>
          </div>
          <h4>Текст сообщения</h4>
          <textarea value={info.text} name="text" onChange={(e)=>handleChange(e)} className="form-control my-2" required></textarea>
          <div>
            <input
              className="form-check-input"
              name="agreement"
              type="checkbox"
              onChange={(e)=>handleChange(e)}
              value={info.agreement}
              id="invalidCheck"
              required
            />
            <label className="form-check-label" htmlFor="invalidCheck">
              Согласие на обработку персональных данных
            </label>
            <div className="invalid-feedback">
              Вы должны согласиться, чтобы отправить отзыв.
            </div>
          </div>
          <button className="btn btn-primary m-2" type="submit">
            Отправить
          </button>
        </form>
      </div>
    </div>
  );
}
