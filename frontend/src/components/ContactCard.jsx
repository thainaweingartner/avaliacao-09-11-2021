import { Delete, Edit } from '@mui/icons-material';
import Avatar from '@mui/material/Avatar';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardHeader from '@mui/material/CardHeader';
import { blue } from '@mui/material/colors';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import React, { useState } from 'react';

import api from '../api/apiConfig';
import EditContactForm from './EditContactForm';

const ContactCard = (props) => {
  const { contact, updateCards, updatecards } = props;
  const [formOpened, setFormOpened] = useState(false);

  const deleteContact = async() => {
    await api.delete(`/person/delete/${contact.id}`);
    updateCards(!updatecards);
  };

  const openEditForm = () => {
    setFormOpened(true);
  }

  const formClose = () => {
    setFormOpened(false);
    updateCards(!updatecards);
  }

  return (
    <>
      <Card sx={{ maxWidth: 345 }}>
        <CardHeader
          avatar={
            <Avatar sx={{ bgcolor: blue[600] }} aria-label="recipe">
              {contact.name.substring(0,1)}
            </Avatar>
          }
          title={contact.name}
          subheader={contact.phone}
        />
        <CardContent>
          <Typography variant="body2" color="text.secondary" data-testId="card-email">
            {contact.email}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton aria-label="edit" onClick={openEditForm}>
            <Edit />
          </IconButton>
          <IconButton aria-label="delete" onClick={deleteContact}>
            <Delete />
          </IconButton>
        </CardActions>
      </Card>
      <EditContactForm openForm={formOpened} handleFormClose={formClose} contact={contact} />
    </>
  );
};

export default ContactCard;
