from .base_command import BaseCommannd
from ..models.entrevista import Entrevista, EntrevistaJsonSchema
from ..session import Session

class GetEntrevista(BaseCommannd):
    def __init__(self, entrevistaId):
        self.entrevistaId = entrevistaId
    
    def execute(self):
        id: int = self.entrevistaId
        session: Session = Session()
        obj: Entrevista = session.get(Entrevista, {'id': id})
        session.close()
        result: dict = EntrevistaJsonSchema().dump(obj)
        return result
        

