class Recommendations : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_recommendations)

        firestore = FirebaseFirestore.getInstance()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, Classify::class.java)
                    startActivityWithAnimation(intent, R.anim.swipe_right_enter, R.anim.swipe_right_exit)
                    finish()
                    true
                }
                R.id.action_event -> {
                    val intent = Intent(this, Event::class.java)
                    startActivityWithAnimation(intent, R.anim.swipe_right_enter, R.anim.swipe_right_exit)
                    true
                }
                R.id.action_recommendation -> {
                    true
                }
                else -> false
            }
        }
        val recyclerView: RecyclerView = findViewById(R.id.recoList_RecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        
        fetchSavedImages { querySnapshot ->
            displaySavedImages(querySnapshot)
        }
    }

    private fun startActivityWithAnimation(intent: Intent, enterAnim: Int, exitAnim: Int) {
        startActivity(intent)
        overridePendingTransition(enterAnim, exitAnim)
    }

    private fun fetchSavedImages(onSuccess: (QuerySnapshot) -> Unit) {
        firestore.collection("Recommendation Saved Images")
            .get()
            .addOnSuccessListener { documents ->
                onSuccess(documents)
            }
            .addOnFailureListener {
            }
    }

    private fun displaySavedImages(querySnapshot: QuerySnapshot) {
        val recyclerView: RecyclerView = findViewById(R.id.recoList_RecyclerView)
        
        val adapter = RecommendationAdapter(this, querySnapshot)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items_linear)// Sample Codes for the Recommendation Feature

// Add actual code here
