import React from 'react'
import ReactDOM from 'react-dom/client'
import AppDev from './AppDev'
import AppProd from './AppProd'
import './style.css'
function App() {
    if (import.meta.env.VITE_NODE_ENV === "development") {
        return <AppDev />;
    } else {
        return <AppProd />;
    }
}
ReactDOM.createRoot(document.getElementById('app')).render(
  <React.StrictMode>
      <App />
  </React.StrictMode>
)
