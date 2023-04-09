export default function Container(props) {
  return (
    <div className="col-sm-9">
      <h2>Книги</h2>
      <div className="container p-4 row" id="books-list">
        {props.books.map((item, index) => (
          <div
            className="card book_card mb-3 me-3"
            key={item.id}
            style={{maxWidth: '540px'}}
          >
            <div className="row g-0 book_item d-flex">
              <div className="col-md-8 d-flex flex-column card_text_div">
                <div className="card-body">
                  <h5 className="card-title">
                    <a href="">{item.name}</a>
                  </h5>
                  <p className="card-text">{item.description}</p>
                  <p className="card-text">Автор: {item.author.name + " " + item.author.surname}</p>
                  <p className="card-text">Жанры: {item.genres.map(elem => elem.name).toString()}</p>
                </div>
                <div className="d-flex justify-content-end">
                  <a
                    className="text-end align-self-end"
                    href="#"
                    onClick={()=>props.onRemove(item.id)}
                  >
                    Удалить
                  </a>
                  <a
                    className="px-2 text-end align-self-end"
                    href="#"
                    onClick={()=>props.onEdit(item.id)}
                  >
                    Изменить
                  </a>
                  <a
                    className="px-2 text-end align-self-end"
                    href="#"
                    onClick={()=>props.onUpdateGenre(item.id)}
                  >
                    Изменить жанры
                  </a>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
