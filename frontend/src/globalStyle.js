/* eslint-disable import/no-named-as-default */
import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  :root {
    --color-main-background: #F6FAFB;
    --color-text-in-primary: #313131;

    --color-primary: #3f51b5;
    --color-secondary: #3f51b5;
    --font-title: Rubik; 
  }
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  html,
  body,
  #root {
    min-height: 100vh;
  }
  body {
    background: var(--color-main-background);
  }
  
  #root {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  body,
  input,
  textarea {
    font: 500 1.6rem Roboto, sans-serif;
    color: var(--color-text-in-primary);
    border-radius: 4px;
  }

  button {
    font: 500 1.5rem Roboto, sans-serif;
    letter-spacing: 0.08rem;
    border-radius: 4px;
  }
`;

export default GlobalStyle;
