import styles from './Toolbar.module.css';

export default function Toolbar(props) {
    function add() {
        props.onAdd();
    }

    function edit() {
        props.onEdit();
    }

    function remove() {
        props.onRemove();
    }

    return (
        <div className="btn-group mt-2" role="group">
            {localStorage.getItem("role") == "ADMIN" &&
            <>
                <button type="button" className={`btn btn-success ${styles.btn}`} onClick={add}>
                    Добавить
                </button>
                <button type="button" className={`btn btn-warning ${styles.btn}`} onClick={edit} >
                    Изменить
                </button >
                <button type="button" className={`btn btn-danger ${styles.btn}`} onClick={remove}>
                    Удалить
                </button >
            </>
            }
        </div >
    );
}