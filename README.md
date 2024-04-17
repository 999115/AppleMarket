## [필요한 기능]

### 1. 메인페이지
- [x] 상품 리스트 나열 - 리사이클러뷰로 구현
- [x] 상단바 - 프래그먼트로 구현
- [x] 각 상품 리스트 클릭 시 상세페이지로 이동
- [ ] 사진 라운드
- [ ] 상품 이름 최대 두줄까지
- [ ] 가격 1000단위 콤마처리
- [ ] 상단바 알림 클릭 시 알림 생성
- [ ] 상단바 장소 클릭 시 다른 장소로 이동 - 다른 프래그먼트 이동까지

### 2. 상세페이지
- [ ] 메인페이지에서 누른 정보 표출 (이미지, 제목, 판매자, 설명, 가격 등)
- [ ] 하단바 - 프래그먼트로 구현
- [ ] 뒤로가기 버튼
- [ ] 채팅하기 버튼 클릭 시 채팅화면으로 이동


### 3. 채팅화면
- [ ] 임시로 구현

 <br>

 ---

## [클래스 다이어그램]
| MainpageActivity | content |
 | - | - |
| + fun setFragment | 프래그먼트 설정 |
| + 장소 setOnClickListener | SecondFragment로 이동 |
| + 알림 setOnClickListener | 알림 표출 |

 <br>

| MainFirstFragment | content |
 | - | - |
| + fun initData | 리스트 for문으로 add (더미데이터) |
| + adapter | MainDefaultAdapter와 연결 및 처리 |
| + click | MainDefaultAdapter에서 받은 클릭동작 처리 |
| - intent | data 전달 |

 <br>

| MainSecondFragment | content |
 | - | - |
| + fun initData | 리스트 for문으로 add (데이터 생성해서 넣기)|
| + adapter | MainDefaultAdapter와 연결 및 처리 |
| + click | MainDefaultAdapter에서 받은 클릭동작 처리 |
| - intent | data 전달 |

 <br>

| MainDefaultAdapter | content |
 | - | - |
| + ViewHolder (inner class) | ViewHolder binding 및 fun setText 생성 |
| + onCreat, onBind | ViewHolder 표출 및 binding |
| + getItemId, getItemCount | item 설정 |

 <br>

| DetailpageActivity | content |
 | - | - |
| + fun setFragment | 프래그먼트 설정 |
| + chat setOnClickListener | Chatpage로 이동 |

 <br>

| DetailFirstFragment | content |
 | - | - |
| - getExtra | intent 받아와 UI 설정 |

 <br>

| ChatpageActivity | content |
 | - | - |
| - getExtra | intent 받아와 UI 설정 |

 <br>

| ProductInfo (Data Class) | content |
 | - | - |
| - int 값 생성자 | R.id 값으로 받음 |

 <br>


## [XML 다이어그램]
| activity_mainpage | content |
 | - | - |
| - constraintlayout | 상단바 (장소 및 알림 버튼) |
| - FrameLayout | Fragment frame |

 <br>

| fragment_main_first | content |
 | - | - |
| - recyclerview | recyclerview |

 <br>

| fragment_main_second | content |
 | - | - |
| - recyclerview | recyclerview |

 <br>

| layout_main_default (LinearLayout) | content |
 | - | - |
| - constraintlayout | 상품 정보 layout |

 <br>

| activity_detailpage | content |
 | - | - |
| - FrameLayout | Fragment frame |
| - constraintlayout | 하단바 (좋아요 버튼, 가격표출 및 채팅 버튼) |

 <br>

| fragment_detail_first | content |
 | - | - |
| - Views | 상품 정보 UI |
| - ImageView (back icon) | 뒤로가기 버튼 |

 <br>
 
