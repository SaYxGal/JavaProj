export default function GenresList(props) {
  return (
    <div className="col-3 genres" style={{backgroundColor: "lightgray", minHeight: "68vh"}}>
      <div className="d-flex flex-row justify-content-between div-with-button">
            <h2>Список жанров</h2>
            {localStorage.getItem("role") == "ADMIN" &&
              <button className="btn btn-primary m-2" type="button" onClick={()=>props.onAdd()}> Добавить </button>
            }
      </div>
      <div className="all_genres">
        <ul id="list-genres">
            {
                props.genres.map((item, index) => (
                    <li key={item.id}>
                        <a href="#">{item.name}</a>
                    </li>
                ))
            }
        </ul>
      </div>
    </div>
  );
}
