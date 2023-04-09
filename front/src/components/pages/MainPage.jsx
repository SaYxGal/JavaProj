import { useState, useEffect } from "react";
import '../../style.css'
import Banner from '../common/Banner';
export default function MainPage(props) {
  return (
    <div className="flex-shrink-0" style={{backgroundColor : 'rgb(255,255,255)'}}>
      <Banner/>
      <h2 className="p-4">Новинки библиотеки</h2>
      <div className="container p-4">
        <div className="main_card row d-flex">
          <div className="col-md-4 mb-3 book_main">
            <div className="card">
              <img
                className="img-fluid"
                src="25756536-author-suhinin_vladimir-kniga_peshka_v_bolshoyi_igre.jpg"
              />
              <div className="card-body">
                <h4 className="card-title">
                  <a href="#">В.Сухинин "Пешка в большой игре"</a>
                </h4>
                <p className="card-text"></p>
              </div>
            </div>
          </div>
          <div className="col-md-4 mb-3 book_main">
            <div className="card">
              <img
                className="img-fluid"
                src="171987-author-zamyatin_evgeniyi-kniga_myi.jpg"
              />
              <div className="card-body">
                <h4 className="card-title">
                  <a href="#">Е.Замятин "Мы"</a>
                </h4>
                <p className="card-text"></p>
              </div>
            </div>
          </div>
          <div className="col-md-4 mb-3 book_main">
            <div className="card">
              <img
                className="img-fluid"
                src="66691848-author-tolstoyi_lev-kniga_voyina_i_mir.jpg"
              />
              <div className="card-body">
                <h4 className="card-title">
                  <a href="#">Л.Толстой "Война и мир"</a>
                </h4>
                <p className="card-text"></p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <h2 className="p-4">
        Самое популярное
        </h2>
        <div className="container p-4">
          <div className="main_card row d-flex">
            <div className="col-md-4 mb-3 book_main">
              <div className="card">
                <img
                  className="img-fluid"
                  src="68221607-author-demina_karina-kniga_nastavnik.jpg"
                />
                <div className="card-body">
                  <h4 className="card-title">
                    <a href="#">К.Демина "Наставник"</a>
                  </h4>
                  <p className="card-text"></p>
                </div>
              </div>
            </div>
            <div className="col-md-4 mb-3 book_main">
              <div className="card">
                <img
                  className="img-fluid"
                  src="68312980-author-kadmon_adam_1-kniga_byitie_budushego_1_pravilnoe_pokolenie.jpg"
                />
                <div className="card-body">
                  <h4 className="card-title">
                    <a href="#">А.Кадмон "Бытие будущего"</a>
                  </h4>
                  <p className="card-text"></p>
                </div>
              </div>
            </div>
            <div className="col-md-4 mb-3 book_main">
              <div className="card">
                <img
                  className="img-fluid"
                  src="25756536-author-suhinin_vladimir-kniga_peshka_v_bolshoyi_igre.jpg"
                />
                <div className="card-body">
                  <h4 className="card-title">
                    <a href="#">В.Сухинин "Пешка в большой игре"</a>
                  </h4>
                  <p className="card-text"></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      
    </div>
  );
}
