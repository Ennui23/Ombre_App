class ResultsRecommendation : AppCompatActivity() {

    private lateinit var selectedEvent: String
    private lateinit var selectedGender: String
    private lateinit var selectedType: String
    private lateinit var selectedColor: String
    private lateinit var selectedPattern: String
    private lateinit var storage: FirebaseStorage
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_recommendation)

        FirebaseApp.initializeApp(this)

        selectedEvent = intent.getStringExtra("event") ?: ""
        selectedGender = intent.getStringExtra("gender") ?: ""
        selectedType = intent.getStringExtra("type") ?: ""
        selectedColor = intent.getStringExtra("color") ?: ""
        selectedPattern = intent.getStringExtra("pattern") ?: ""

        storage = FirebaseStorage.getInstance()
        firestore = FirebaseFirestore.getInstance()
        
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        
        val textTags: TextView = findViewById(R.id.textTags)
        
        queryRecommendations(selectedEvent, selectedGender, selectedType, selectedColor, selectedPattern) { documents ->
            displayRecommendations(documents)

            if (!documents.isEmpty) {
                val firstDocument = documents.documents[0]
                val event = firstDocument.getString("event") ?: ""
                val type = firstDocument.getString("type") ?: ""
                val color = firstDocument.getString("color") ?: ""
                val pattern = firstDocument.getString("pattern") ?: ""

                val tagsText = buildString {
                    if (event.isNotEmpty()) append("$event  ")
                    if (type.isNotEmpty()) append("$type  ")
                    if (color.isNotEmpty()) append("$color  ")
                    if (pattern.isNotEmpty()) append("$pattern ")
                }
                textTags.text = tagsText
            }
        }

// Sample Codes for Recommendation Results

// Add actual code here
