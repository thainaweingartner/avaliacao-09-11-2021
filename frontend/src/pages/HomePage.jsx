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

  const getContacts = async () => {
    const { data } = await api.get('/person/');
    setContacts(data);
  };

  useEffect(() => {
    getContacts();
  }, []);

  const formOpen = () => {
    setFormOpened(true);
  }

  const formClose = () => {
    setFormOpened(false);
  }

  return (
    <Root>
      <Typography variant="h2">Welcome to your Contact List</Typography>
      <Button variant="outlined" onClick={formOpen}>Add new Contact</Button>
      <Content>
        {contacts.map(contact => (
            <ContactCard contact={contact}/>
        ))}
      </Content>
      <AddContactForm openForm={formOpened} handleFormClose={formClose} />
    </Root>
  );
};

export default HomePage;
