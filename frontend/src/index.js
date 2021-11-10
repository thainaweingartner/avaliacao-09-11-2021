import React from 'react';
import ReactDOM from 'react-dom';
import GlobalStyle from './globalStyle';

import AppRoutes from './AppRoutes';

ReactDOM.render(
  <React.StrictMode>
    <GlobalStyle />
    <AppRoutes />
  </React.StrictMode>,
  document.getElementById('root')
);