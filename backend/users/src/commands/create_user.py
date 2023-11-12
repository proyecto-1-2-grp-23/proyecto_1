from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.candidato import CandidatoSchema, Candidato
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists


class CreateUser(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        # try:
        user_data = {
            "correo": self.data.pop("correo"),
            "password": self.data.pop("password"),
            "ciudad": self.data.pop("ciudad"),
            "pais": self.data.pop("pais"),
        }
        posted_user = UserSchema(only=("correo", "password", "ciudad", "pais")).load(
            user_data
        )
        user = User(**posted_user)

        session = Session()

        if self.correo_exist(session, user_data["correo"]):
            session.close()
            raise UserAlreadyExists()

        session.add(user)
        session.commit()
        new_user = CreatedUserJsonSchema().dump(user)
        session.close()

        candidato_data = {
            "nombreCompleto": self.data.pop("nombreCompleto"),
            "telefono": self.data.pop("telefono"),
            "edad": self.data.pop("edad"),
            "idiomas": self.data.pop("idiomas"),
            "rasgosPersonalidad": self.data.pop("rasgosPersonalidad"),
            "idUsuario": new_user["id"],
        }

        posted_candidato = CandidatoSchema(
            only=(
                "telefono",
                "nombreCompleto",
                "edad",
                "idiomas",
                "rasgosPersonalidad",
                "idUsuario",
            )
        ).load(candidato_data)

        candidato = Candidato(**posted_candidato)

        session = Session()
        session.add(candidato)
        session.commit()
        new_user = CreatedUserJsonSchema().dump(user)

        session.close()

        return new_user

    # except TypeError:
    #     raise IncompleteParams()

    def correo_exist(self, session, correo):
        return len(session.query(User).filter_by(correo=correo).all()) > 0
