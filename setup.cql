CREATE(_206:`Employee` {`age`:     15, `email`: 'vmjari@outlook.com', `experience`: 1, `gender`: 'm', `name`: 'Jari',
                        `surname`: 'Van Melckebeke'})
CREATE (_207:`Project` {`cost`: 0.000000, `name`: 'T.E.R.E.S.A.', `onTime`: true, `version`: '0.0.1'})
CREATE (_208:`Client` {`experience`: 10, `name`: 'noobcorp'})
CREATE (_209:`Sector` {`name`: 'random'})
CREATE(_210:`Employee` {`age`:     16, `email`: 'a.waegeman@telenet.be', `experience`: 1, `gender`: 'v',
                        `name`:    'Amber',
                        `surname`: 'Waegeman'})
CREATE (_211:`Project` {`cost`: 50.000000, `name`: 'DroneOS', `version`: '0.0.1'})
CREATE (_212:`Client` {`experience`: 6, `name`: 'Colruyt'})
CREATE
  (_213:`Employee` {`age`:     15, `email`: 'bram.depinnewaert@gmail.com', `experience`: 1, `gender`: 'm',
                    `name`:    'Bram',
                    `surname`: 'De Pinnewaert'})
CREATE (_214:`Project` {`cost`: 1005.000000, `name`: 'LiveSimulator', `onTime`: true, `version`: '0.1.2'})
CREATE (_215:`Client` {`experience`: 1, `name`: 'Real Dolmen'})
CREATE
  (_217:`Employee` {`age`:     14, `email`: 'stijn.vanverckem@yahoo.com', `experience`: 1, `gender`: 'm',
                    `name`:    'Stijn',
                    `surname`: 'Van Vreckem'})
CREATE (_218:`Project` {`cost`: 57.000000, `name`: 'MkmJgh', `onTime`: true, `version`: '1.2'})
CREATE(_220:`Employee` {`age`:     13, `email`: 'lars.kestemont@gmail.com', `experience`: 1, `gender`: 'm',
                        `name`:    'Lars',
                        `surname`: 'Kestemont'})
CREATE (_221:`Project` {`cost`: 17711.500000, `name`: 'JDG', `version`: '0.9'})
CREATE (_239:`Project` {`cost`: 0.300000, `name`: 'stageopdracht', `version`: '0.1'})
CREATE (_206)-[:`WorkedOn` {`onTime`: false, `projectNr`: 0, `role`: 'stagair', `score`: 0.000000}]->(_239)
CREATE (_206)-[:`WorkedOn` {`nOfProject`: '1', `role`: 'Lead developer', `score`: 75.000000}]->(_207)
CREATE (_208)-[:`Issued`]->(_207)
CREATE (_209)-[:`IsSectorFor`]->(_208)
CREATE (_209)-[:`IsSectorFor`]->(_212)
CREATE (_209)-[:`IsSectorFor`]->(_215)
CREATE (_210)-[:`WorkedOn` {`onTime`: false, `projectNr`: 0, `role`: 'Web design', `score`: 59.000000}]->(_211)
CREATE (_212)-[:`Issued`]->(_218)
CREATE (_212)-[:`Issued`]->(_211)
CREATE (_212)-[:`Issued`]->(_221)
CREATE (_213)-[:`WorkedOn` {`nOfProject`: '1', `role`: 'level designer', `score`: 70.000000}]->(_214)
CREATE (_215)-[:`Issued`]->(_239)
CREATE (_215)-[:`Issued`]->(_214)
CREATE (_217)-[:`WorkedOn` {`nOfProject`: '1', `role`: 'boekhouder', `score`: 70.000000}]->(_218)
CREATE (_217)-[:`WorkedOn` {`nOfProject`: '1', `role`: 'boekhouder', `score`: 75.000000}]->(_207)
CREATE (_220)-[:`WorkedOn` {`onTime`: false, `projectNr`: 0, `role`: 'astrofysicus', `score`: 95.000000}]->(_221)
;
