/* eslint-disable jest/no-mocks-import */
import { render, screen, fireEvent } from '@testing-library/react';
import "@testing-library/jest-dom";
import axios from "../__mocks__/axios"
import AddContactForm from '../components/AddContactForm';

const mockedContact = { 
  name: "Teste",
  phone: "84995960684",
  email: "teste@teste.com",
}

const openForm = true;

const handleFormClose = jest.fn();

describe(`${AddContactForm.name}`, () => {
  
  test('should fill the input with corret email', async () => {
    render(
      <AddContactForm 
        openForm={openForm}
        handleFormClose={handleFormClose}
      />
    );
    
    const emailFild = screen.getByTestId("email").querySelector('input');
    
    fireEvent.change(emailFild, { target: { value: "teste@teste.com"}});
    expect(emailFild.value).toBe("teste@teste.com");

  })

  
  test('should not send form if a field is not complete', async () => {
    axios.post.mockImplementation(() => Promise.resolve());

    render(
      <AddContactForm 
        openForm={openForm}
        handleFormClose={handleFormClose}
      />
    );
    
    const emailFild = screen.getByTestId("email").querySelector('input');
    const nameFild = screen.getByTestId("name").querySelector('input');
    const phoneFild = screen.getByTestId("phone").querySelector('input');
    const button = screen.getByText("SAVE CONTACT");

    fireEvent.change(emailFild, { target: { value: "teste@teste.com"}});
    fireEvent.change(nameFild, { target: { value: "Teste"}});
    expect(emailFild.value).toBe("teste@teste.com");
    expect(nameFild.value).toBe("Teste");
    expect(phoneFild.value).toBe("");
    fireEvent.click(button);

    expect(axios.post).toHaveBeenCalledTimes(0);
  })

  
  test('should send form if all fields are complete', async () => {
    axios.post.mockImplementation(() => Promise.resolve(mockedContact));

     render(
      <AddContactForm 
        openForm={openForm}
        handleFormClose={handleFormClose}
      />
    );

    const emailFild = screen.getByTestId("email").querySelector('input');
    const nameFild = screen.getByTestId("name").querySelector('input');
    const phoneFild = screen.getByTestId("phone").querySelector('input');
    const button = screen.getByText("SAVE CONTACT");

    fireEvent.change(emailFild, { target: { value: "teste@teste.com"}});
    fireEvent.change(nameFild, { target: { value: "Teste"}});
    fireEvent.change(phoneFild, { target: { value: "84995960684"}});
    expect(emailFild.value).toBe("teste@teste.com");
    expect(nameFild.value).toBe("Teste");
    expect(phoneFild.value).toBe("84995960684");
    fireEvent.click(button);

    expect(axios.post).toHaveBeenCalled();
  })
})