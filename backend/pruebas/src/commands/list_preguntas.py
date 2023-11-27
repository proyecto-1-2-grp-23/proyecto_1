from .base_command import BaseCommannd
from ..models.pregunta import Pregunta, PreguntaSchema, CreatedPreguntaJsonSchema
from ..models.respuesta import Respuesta, RespuestaSchema, CreatedRespuestaJsonSchema
from ..session import Session


class ListPreguntas(BaseCommannd):
    def __init__(self):
        pass

    def execute(self):
        session: Session = Session()
        preguntas = session.query(Pregunta).all()
        results = []
        for pregunta in preguntas:
            result: dict = PreguntaSchema().dump(pregunta)
            respuestas = (
                session.query(Respuesta)
                .filter(Respuesta.idPregunta == pregunta.id)
                .all()
            )
            result["respuestas"] = RespuestaSchema(many=True).dump(respuestas)
            results.append(result)
        session.close()
        return results
