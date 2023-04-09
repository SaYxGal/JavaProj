export default function ForumDiscussion(props) {
  return (
    <div className="card text-end mb-3" style={{backgroundColor : 'lightgray'}}>
      <div className="row g-0 justify-content-between forum_page">
        <div className="col-md-4 forum_img">
          <img
            className="img-fluid rounded-start"
            src="x5s0payf.jpg"
            alt="..."
          />
        </div>
        <div className="col-md-8">
          <div className="card-body">
            <a className="text-black" href="#">
              <h5 className="card-title">{props.title}</h5>
            </a>
            <p className="card-text">Создатель: {props.username}</p>
            <p className="card-text">
              <small className="text-muted">{props.text}</small>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
