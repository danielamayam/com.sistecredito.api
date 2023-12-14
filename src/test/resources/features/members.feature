Feature: LLamando a los endpoints de Members

  Como Qa automatizador
  Quiero ejecutar este reto
  Para validar estos endpoints

  @getMembers
  Scenario Outline: Usuario lista todos los miembros Get
    When ejecuto el endpont get members
    Then válido la estructura de los datos "getMembers"
    And válido los datos recibidos "<response>"

    Examples:
      | response                                                                                                                                                              |
      | [{'id':1,'name':'Monil','gender':'Female'},{'id':2,'name':'Ramona','gender':'Female'},{'id':3,'name':'Lion','gender':'Male'},{'id':4,'name':'Shawn','gender':'Male'}] |

  @postMembers
  Scenario Outline: Usuario registra un miembro POST
    When ejecuto el endpoint post members
      | name   | gender   |
      | <name> | <gender> |
    Then válido la respuesta del enpoint "<name>" "<gender>" "<response>"

    Examples:
      | name   | gender | response     |
      | Daniel | Male   | responsePost |

  @putMembers
  Scenario Outline: Usuario actualiza un miembro PUT
    When ejecuto el endpoint put members "<id>"
      | name   | gender   |
      | <name> | <gender> |
    Then válido la respuesta del enpoint "<name>" "<gender>" "<response>"

    Examples:
      | id | name   | gender | response    |
      | 5  | Daniel | Male   | responsePut |

  @DeleteMembers
  Scenario Outline: Usuario elimina un miembro DELETE
    When ejecuto el endpoint delete members "<id>"
    Then válido el msg del enpoint "<response>"

    Examples:
      | id | response    |
      | 5  | Member with id 5 is deleted successfully |
