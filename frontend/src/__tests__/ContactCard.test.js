import '@testing-library/jest-dom';

import { render, screen } from '@testing-library/react';

import ContactCard from '../components/ContactCard';

const mockedContact = { 
  name: "Teste",
  phone: "84995960684",
  email: "teste@teste.com",
};
const updateCards = false;

const updatecards = () => {return false};

describe(`${ContactCard.name}`, () => {
  
  test('should fill the card with corret email', async () => {
    render(
      <ContactCard 
        contact={mockedContact} 
        updateCards={updateCards} 
        updatecards={updatecards}
      />
    );
    
    const emailFild = screen.getByTestId("card-email");
    expect(emailFild).toHaveTextContent("teste@teste.com");
  })
})