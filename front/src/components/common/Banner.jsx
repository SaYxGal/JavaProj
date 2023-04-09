import { useEffect } from "react";
import React, { useState } from 'react';
export default function Banner(props) {
    const [bannerState, setBannerState] = useState(false);
    useEffect(()=>{
        const timer = window.setInterval(() => {
            setBannerState(prevbannerState => !prevbannerState);
          }, 5000);
        return () => {
            window.clearInterval(timer);
        }
    },[]);
    return (
        <div className="p-4 d-flex justify-content-center" id="banner">
        {
            bannerState
            ? <img className="img-fluid" src="image_image_1199445.png"/>
            : <img className="img-fluid" src="image.png"/>
        }
        </div>
    );
  }