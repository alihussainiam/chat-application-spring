## Requirements clarification:

```
1. Receive new messages from the client
```

Multiple Clients sent messages to one chat group
All new Clients subsribe to one chat group
When a message is send to it should be append to group chat messages (Array chronological ordered)

```
2. List all messages in chronological order for the client
```

- When a clients subscribe they get the collection of all the messages from the single chat group

- (Optional) Pagination for scrolling using lazy fetching to their local collection using scroll or click fetched

## Design Considerations:

### Communication

1. Http
2. Websockets
3. Server Side Events (WebFlux)

## Plan:

FrontEnd :

1. Start with Basic server side served html
2. Create React App
   Login/Register Page
   Main Chat Page

Backend :

1. Create Spring Boot app and configure web socket skelaton w/o persitances layer
2. Add persistance side Mongo/Redis for new users to get chat history

### Containerize:
1. Bundle react app and serve using server
2. Add database
3. Add Nginx (optional)


## API Design

GET /api/v1/messages 

```
[{
    sender: username,
    content: message,
    type: 'CHAT' | 'JOIN' | 'LEAVE'
}]
```

WS /app/chatroom/public

STOMP /app/chat.addUser
```
{
    sender: username, type: 'JOIN'
}
```

STOMP /app/chat.sendMessage
```
{
    sender: username,
    content: messageInput.value,
    type: 'CHAT'
}
```

## Data Payload
```
{
    username:string,
    email:string (Optional)
}
```


Chat
id:string
messages: message[]

Message
sender: User
text: string(256)
date_timestamp: Date

## High Level Design

Insert Images

## User Stories

- First user joins a chat with creating the group
- Last user destroys the chat group
- Subsequent user joins the same group
- Rejoin with same session (Optional)

## User Session Options:

- Create Fingerprint with browser
- Login/Register

### Reponsiveness

- For Mobile responsive and Web using Tailwind (Optional)

## Future Scope

- Add nginx proxy to serve bundle through s3 or CDN
