import { Button } from '@mui/material';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState } from 'react';

import api from '../api/apiConfig';
import AddContactForm from '../components/AddContactForm';
import ContactCard from '../components/ContactCard';
import { Content, Root } from './HomePageStyles';

const HomePage = () => {
  const [contacts, setContacts] = useState([]);
  const [formOpened, setFormOpened] = useState(false);
  const [updateCards, setUpdateCards] = useState(true);

  const getContacts = async () => {
    const { data } = await api.get('/person/');
    setContacts(data);
  };

  useEffect(() => {
    getContacts();
  }, [updateCards]);

  const formOpen = () => {
    setFormOpened(true);
  }

  const formClose = () => {
    setFormOpened(false);
    setUpdateCards(!updateCards);
  }

  return (
    <Root>
      <Typography variant="h2">Welcome to your Contact List</Typography>
      <Button variant="outlined" onClick={formOpen}>Add new Contact</Button>
      <Content>
        {contacts.map(contact => (
            <ContactCard 
              contact={contact} 
              key={contact.id} 
              updateCards={setUpdateCards} 
              updatecards={updateCards}
            />
        ))}
      </Content>
      <AddContactForm openForm={formOpened} handleFormClose={formClose} />
      <Typography variant="body2">Developed by Thainá Weingartner Chagas in 2021</Typography>
    </Root>
  );
};

export default HomePage;
