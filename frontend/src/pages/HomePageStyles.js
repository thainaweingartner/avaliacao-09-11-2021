import styled from 'styled-components';

export const Root = styled.div`
  padding: 50px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 20px;
  width: 90%;
  justify-content: top;
  height: 100vh;

  h2, p {
    align-self: center;
  }
`;

export const Content = styled.div`
    display: flex;
    width: 100%;
    align-items: flex-start;
    gap: 20px 20px;
    flex-wrap: wrap;
    height: 100%;
`;
