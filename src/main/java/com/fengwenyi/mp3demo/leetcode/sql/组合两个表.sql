SELECT p.FirstName,p.LastName,a.City,a.State FROM person p LEFT JOIN address a ON p.PersonId=a.PersonId


select FirstName, LastName, City, State
from Person left join Address
on Person.PersonId = Address.PersonId
